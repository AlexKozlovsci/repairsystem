package repairSystem.documentGeneration;

import repairSystem.dao.PricelistRepository;
import repairSystem.model.Pricelist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Глеб on 25.05.2017.
 */
public class DataLoad {


    public static List<String[]> getPriceCurrentList(PricelistRepository pricelistRepository){
        List<String[]> priceCurrentList = new ArrayList<>();
        priceCurrentList.add(new String[]{"Device", "Action", "Cost"});
        List<Pricelist> prices = pricelistRepository.findAll();
        for (Iterator<Pricelist> i = prices.iterator(); i.hasNext();) {
            Pricelist item = i.next();
            Integer temp = (int)item.getCost();

            priceCurrentList.add(new String[]{item.getDeviceType(), item.getAction(), temp.toString()});
        }
        return priceCurrentList;
    }
}
