package study.hitchhiking.VO;

import lombok.Getter;
import study.hitchhiking.config.CONFIG;
import study.hitchhiking.pojo.Orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;



@Getter
public class orderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderID;

    private Long UserID;

    private String role;

    private String orderstatus;

    private BigDecimal orderprice;

    private String carID;

    private String getonposition;

    private String getontime;

    private String getoffposition;

    private String threshold;

    private String destination;

    private String departtime;

    public orderVO(Orders order){
        this.orderID = order.getOrderID();
        this.orderstatus = order.getOrderstatus();
        this.orderprice = order.getOrderprice();
        this.carID = order.getCarID();
        this.getonposition = order.getGetonposition();
        this.getoffposition = order.getGetoffposition();
        this.threshold = order.getThreshold();
        this.destination = order.getDestination();
        this.role = order.getRole();
        this.UserID = order.getUserID();
        if(null != order.getDeparttime()){
            departtime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getDeparttime());
        }else{
            departtime = CONFIG.DEFAULT_DATE;
        }
        if(null != order.getGetontime()){
            getontime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getGetontime());
        }else{
            getontime = CONFIG.DEFAULT_DATE;
        }
    }
}
