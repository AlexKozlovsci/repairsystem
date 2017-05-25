package repairSystem.documentGeneration;

import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.Workorder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    public static List<String[]> getDetailList( WorkorderRepository workorderRepository, int orderId){

        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        List<String[]> detailList = new ArrayList<>();
        detailList.add(new String[]{"Name", "Count"});
        Set<Detail> detail = workorder.getDetail();
        for (Iterator<Detail> i = detail.iterator(); i.hasNext();) {
            Detail item = i.next();
            Integer temp = (int)item.getCount();
            detailList.add(new String[]{item.getName(), temp.toString()});
        }
        return detailList;
    }
}
