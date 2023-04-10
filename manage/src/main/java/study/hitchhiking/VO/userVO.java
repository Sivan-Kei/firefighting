package study.hitchhiking.VO;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.CarService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;


@Getter
public class userVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userID;

    private String password;

    private String name;

    private String sex;

    private String phonenumber;

    private String identification;

    private String createtime;

    private List<Car> car;

    private static final String DEFAULT_DATE = "****-**-** **:**:**";

    public userVO(User user,CarService carService){
        this.userID = user.getUserID();
        this.password = user.getPassword();
        this.name = user.getName();
        this.identification = user.getIdentification();
        this.phonenumber = user.getPhonenumber();
        this.sex = user.getSex();
        if(null != user.getCreatetime()){
            createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatetime());
        }else{
            createtime = DEFAULT_DATE;
        }

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.like("userID",userID);
        car = carService.list(wrapper);
    }

    public int getCarNumber(){
        return car.size();
    }


}
