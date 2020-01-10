package com.amazonaws.demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class S3CRUDdemo {
	static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("ap-southeast-1").build();

	public void list_buckets() {
		List<Bucket> buckets = s3.listBuckets();
		System.out.println("Your Amazon S3 buckets are:");
		for (Bucket b : buckets) {
			System.out.println("* " + b.getName());
		}
	}

	public Bucket create_bucket(String bucket_name) {
		Bucket b = null;
		if (s3.doesBucketExistV2(bucket_name)) {
			System.out.format("Bucket" + bucket_name + " already exists.\n");
		} else {
			try {
				b = s3.createBucket(bucket_name);
			} catch (AmazonS3Exception e) {
				System.err.println(e.getErrorMessage());
			}
		}
		return b;
	}

	public void copy_bucket(String origin_bucket, String to_bucket) {
		ListObjectsV2Result result = s3.listObjectsV2(origin_bucket);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			s3.copyObject(origin_bucket, os.getKey(), to_bucket, os.getKey());
		}

		System.out.println("Done!");
	}

	public void delete_objects(String bucket_name)
	{
		VersionListing version_listing = s3.listVersions(
		        new ListVersionsRequest().withBucketName(bucket_name));
		while (true) {
		    for (Iterator<?> iterator =
		         version_listing.getVersionSummaries().iterator();
		         iterator.hasNext(); ) {
		        S3VersionSummary vs = (S3VersionSummary) iterator.next();
		        s3.deleteVersion(
		                bucket_name, vs.getKey(), vs.getVersionId());
		    }

		    if (version_listing.isTruncated()) 
		    {
		        version_listing = s3.listNextBatchOfVersions(
		                version_listing);
		    } 
		    else 
		    {
		        break;
		    }
		}
	}

	public static void main(String args[]) {
		S3CRUDdemo obj = new S3CRUDdemo();
		Scanner sc = new Scanner(System.in);
		obj.list_buckets();
		System.out.println("Enter bucket name:-");
		String str = sc.next();
		obj.create_bucket(str);
		obj.list_buckets();

		System.out.println("Enter the origin bucket name:-");
		String origin_bucket = sc.next();
		System.out.println("Enter the destination bucket name:-");
		String to_bucket = sc.next();

		obj.copy_bucket(origin_bucket, to_bucket);
		obj.delete_objects(origin_bucket);
		s3.deleteBucket(origin_bucket);
		System.out.println("Done!");
		sc.close();
	}

}
