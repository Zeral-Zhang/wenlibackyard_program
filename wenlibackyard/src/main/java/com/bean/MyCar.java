package com.bean;

import java.util.HashMap;
import java.util.Map;

import com.po.Productinfo;

public class MyCar {
	private Map<Integer, ShopCarItem> items = new HashMap<Integer, ShopCarItem>();
    private float sumPrice;//总价
    
    public Map<Integer, ShopCarItem> getItems()
    {
        return items;
    }
    
    public void setItems(Map<Integer, ShopCarItem> items)
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
	
	public Map<Integer, ShopCarItem> add(Productinfo product,int num){
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
	
	public Map<Integer, ShopCarItem> dec(Productinfo product, int num) {
		ShopCarItem sc = items.get(product.getProductId());
		if (sc != null) {
			sc.setNum(sc.getNum() - num);
			sc.setPrice((float) (sc.getNum() * product.getPrice()));
		} else {
			items.clear();
		}
		return items;
	}


	public Map<Integer, ShopCarItem> remove(Integer id) {
		if(items.size() > 0) {
			items.remove(id);
		}
		return items;
	}

	public float sumPrice() {
		this.setSumPrice(0);
		if (items != null && items.size() > 0) {
			for (Integer key : items.keySet()) {
				this.sumPrice += items.get(key).getPrice();
			}
			
		}
		return sumPrice;
	}

}
