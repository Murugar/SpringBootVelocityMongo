package com.iqmsoft.boot.velocity.shorturl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ShortUrlRepositoryImpl implements ShortUrlRepositoryCustom {

	private final MongoOperations operations;

	@Autowired
	public ShortUrlRepositoryImpl(MongoTemplate operations) {
		this.operations = operations;
	}

	
	@Override
	public Integer getTotalRedirectSum() {

		if (operations != null) {
			
			log.debug("Not Null Operations");
			
			AggregationResults<SumResult> results = 
					operations.aggregate(Aggregation.newAggregation(ShortUrl.class,
					match(Criteria.where("redirectCount").gte(0)), 
					Aggregation.group().sum("redirectCount").as("total")),
					SumResult.class);
			
			if(results != null)
			{
				if (results.getUniqueMappedResult() != null)
				{
				   log.debug("Not Null Operations Query");	
				   log.debug("Total " + results.getUniqueMappedResult().getTotal());	
				   
				   return results.getUniqueMappedResult().getTotal();
				}
				else 
				{
					log.debug("Null Operations Query");	
					return 0;
				}
			}
			else
			{
				log.debug("Results Null Operations Query");	
				return 0;
			}
			
		}
		else 
		{
			log.debug("Null Operations");
			return 0;
		}

	}

}
