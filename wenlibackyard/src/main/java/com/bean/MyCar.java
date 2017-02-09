package com.bean;

import java.util.HashMap;
import java.util.Map;

import com.po.ProductInfo;

public class MyCar {
	private Map<String, ShopCarItem> items = new HashMap<String, ShopCarItem>();
    private float sumPrice;//总价
    
    public Map<String, ShopCarItem> getItems()
    {
        return items;
    }
    
    public void setItems(Map<String, ShopCarItem> items)
    {
        this.items = items;
    }
    
    public float getSumPrice() {
    	 //计算总价
        int sumPrice=0;
        for(ShopCarItem item:items.values())
        {
            sumPrice += item.getPrice();
        }
		return sumPrice;
	}

	public void setSumPrice(float sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	public Map<String, ShopCarItem> add(ProductInfo product,int num){
		ShopCarItem sc = items.get(product.getProductId());
		if(null != sc){
			if(sc.getNum() == 1 && num < 0) {
				sc.setNum(1);
			} else if(sc.getNum()+num > product.getNumber()) {
				sc.setNum(product.getNumber());
			} else {
				sc.setNum(sc.getNum()+num);
			}
			sc.setPrice(sc.getNum()*product.getPrice());
		}else{
			ShopCarItem shopcar = new ShopCarItem();
			shopcar.setNum(num);
			shopcar.setPrice(product.getPrice());
			shopcar.setProduct(product);
			items.put(product.getProductId(), shopcar);
		}
		
		return items;
	}
	
	public Map<String, ShopCarItem> dec(ProductInfo product, int num) {
		ShopCarItem sc = items.get(product.getProductId());
		if (sc != null) {
			sc.setNum(sc.getNum() - num);
			sc.setPrice((float) (sc.getNum() * product.getPrice()));
		} else {
			items.clear();
		}
		return items;
	}


	public Map<String, ShopCarItem> remove(String productId) {
		if(items.size() > 0) {
			items.remove(productId);
		}
		return items;
	}

	public float sumPrice() {
		this.setSumPrice(0);
		if (items != null && items.size() > 0) {
			for (String key : items.keySet()) {
				this.sumPrice += items.get(key).getPrice();
			}
			
		}
		return sumPrice;
	}

}
