package com.aditya.demof.daos;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.aditya.demof.models.Product;

@Repository
public class ProductRepository {

	List<Product> products = new LinkedList<>();
    
    @PostConstruct
    public void init() {
        System.out.println("Generate sample data");
        products.add(new Product("Product A",101,"Sample product A",120D));
        products.add(new Product("Product B",102,"Sample product B",110.3D));
        products.add(new Product("Product C",103,"Sample product C",320.67D));
        products.add(new Product("Product D",104,"Sample product D",151.4D));
    }
    
    public Product findById(int id) {
        Optional<Product> px = products.stream().filter(p->p.getId()==id).findFirst();
        if(px.isPresent())
            return px.get();
        else
            return null;
    }
    
    public void save(Product product)
    {
    	Product temp= findById(product.getId());
    	if (temp!=null)
    	{
    		throw new RuntimeException("Product does exit");
    	}
    	products.add(product);
    }
    public void update(Product product)
    {
    	Product temp= findById(product.getId());
    	if(temp==null)
    	{
    		throw new RuntimeException("Product does not exit");
    	}
    	temp.setDescription(product.getDescription());
		temp.setPrice(product.getPrice());
		temp.setName(product.getName());
    }
    public void delete(int id)
    {
    	Product temp= findById(id);
    	if(temp==null)
    	{
    		throw new RuntimeException("Product does not exit");
    	}
    	products.remove(temp);
    }
}