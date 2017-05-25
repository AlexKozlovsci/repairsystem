package repairSystem.documentGeneration;


import org.apache.log4j.Logger;
import repairSystem.controller.EngineerController;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.User;
import repairSystem.model.Workorder;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Глеб on 25.05.2017.
 */
public class DataLoad {
    private static final Logger log = Logger.getLogger(EngineerController.class);

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


    public static List<String[]> getDetailList( WorkorderRepository workorderRepository, int orderId) {

        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        List<String[]> detailList = new ArrayList<>();
        detailList.add(new String[]{"Name", "Count"});
        Set<Detail> detail = workorder.getDetail();
        for (Iterator<Detail> i = detail.iterator(); i.hasNext(); ) {
            Detail item = i.next();
            Integer temp = (int) item.getCount();
            detailList.add(new String[]{item.getName(), temp.toString()});
        }
        return detailList;
    }

    public static List<String[]> getMonthReportList(WorkorderRepository workorderRepository, UserRepository userRepository){
        List<String[]> ordersCurrentList = new ArrayList<>();
        ordersCurrentList.add(new String[]{"Order №", "Manager", "Engineer", "Total cost"});

        Date curDate = new Date();
        Date oldMonth = new Date();
        if (oldMonth.getMonth() != 1){
            oldMonth.setMonth(oldMonth.getMonth() - 1);
        }else{
            oldMonth.setMonth(12);
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");

        List<Workorder> orders = workorderRepository.findAll();
        for(Workorder wo: orders){
            if (wo.getComplete_at() != null){
                String temp = wo.getComplete_at();
                try{
                    Date orderDate = format.parse(temp);
                    if (orderDate.after(oldMonth) && orderDate.before(curDate)){
                        Integer tempId = (int)wo.getId();

                        Integer managerId = (int)wo.getId_manager();
                        User uManager = userRepository.findById(managerId);
                        String managerName = uManager.getName().concat(" ").concat(uManager.getSecondname());

                        Integer engineerId = (int)wo.getId_engineer();
                        User uEngineer = userRepository.findById(engineerId);
                        String engineerName = uEngineer.getName().concat(" ").concat(uEngineer.getSecondname());

                        Set<Detail> details =  wo.getDetail();
                        Set<Pricelist> pricelists =  wo.getPricelists();
                        Integer totalCost = 0;
                        for (Detail detail: details)
                            totalCost += (int)detail.getCost();
                        for (Pricelist pricelist: pricelists)
                            totalCost += (int)pricelist.getCost();

                        ordersCurrentList.add(new String[]{tempId.toString(), managerName, engineerName,totalCost.toString()});
                    }

                }
                catch (Exception e)
                {
                    log.info("error");
                }
            }
        }
        return ordersCurrentList;
    }


    public static List<String[]> getProcurementSheetList(DetailRepository detailRepository){
        List<String[]> detailCurrentList = new ArrayList<>();

        detailCurrentList.add(new String[]{"Detail", "Cost", "Count"});

        List<Detail> details = detailRepository.findAll();

        for (Iterator<Detail> i = details.iterator(); i.hasNext();) {
            Detail item = i.next();
            if(item.getCount() == 0){
                Integer temp = (int)item.getCost();
                Integer random = 1 + (int) (Math.random() * 100);
                detailCurrentList.add(new String[]{item.getName(), temp.toString(), random.toString()});
            }
        }
        return detailCurrentList;
    }


    public static List<String[]> getPaymentRecipeList(WorkorderRepository workorderRepository, int id){
        List<String[]> finalList = new ArrayList<>();

        finalList.add(new String[]{"Device", "Action", "Cost"});

        Workorder workorder = workorderRepository.findById(id);
        Set<Pricelist> prices = workorder.getPricelists();
        Set<Detail> details = workorder.getDetail();

        for(Pricelist price: prices){
            Integer temp = (int)price.getCost();
            finalList.add(new String[]{price.getDeviceType(), price.getAction(), temp.toString()});
        }

        finalList.add(new String[]{"Details"});
        finalList.add(new String[]{"Name", "Cost"});
        for(Detail detail: details){
            Integer temp = (int)detail.getCost();
            finalList.add(new String[]{detail.getName(), temp.toString()});
        }

        Integer totalCost = 0;
        for (Detail detail: details)
            totalCost += (int)detail.getCost();
        for (Pricelist pricelist: prices)
            totalCost += (int)pricelist.getCost();

        finalList.add(new String[]{"Total cost: ", totalCost.toString()});
        return finalList;
    }

    public static List<String[]> getReport(WorkorderRepository workorderRepository, int id){
        List<String[]> finalList = new ArrayList<>();

        finalList.add(new String[]{"Device", "Action", "Cost"});


        Workorder workorder = workorderRepository.findById(id);
        Set<Pricelist> prices = workorder.getPricelists();
        Set<Detail> details = workorder.getDetail();

        for(Pricelist price: prices){
            Integer temp = (int)price.getCost();
            finalList.add(new String[]{price.getDeviceType(), price.getAction(), temp.toString()});
        }


        return finalList;
    }
}
