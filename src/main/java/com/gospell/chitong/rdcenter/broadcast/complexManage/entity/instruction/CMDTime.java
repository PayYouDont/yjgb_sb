package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.util.Calendar;
import java.util.Date;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.GsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_time
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDTime extends CMD {

    /**
     * 年，如 2014 年：0x7de
     */
    private Integer wyear;

    /**
     * 月
     */
    private Integer imonth;

    /**
     * 日
     */
    private Integer iday;

    /**
     * 时
     */
    private Integer ihour;

    /**
     * 分
     */
    private Integer iminute;

    /**
     * 秒
     */
    private Integer isecond;
    //以下不保存数据库
    @GsonIgnore
    protected Date time;
    @GsonIgnore
    protected Calendar calendar = Calendar.getInstance ();

    public Date getTime(){
        if(wyear!=null){
            calendar.set(Calendar.YEAR, wyear);
        }
        if(imonth!=null){
            calendar.set(Calendar.MONTH, imonth);
        }
        if(iday!=null){
            calendar.set(Calendar.DAY_OF_MONTH, iday);
        }
        if(ihour!=null){
            calendar.set(Calendar.HOUR, ihour);
        }
        if(iminute!=null){
            calendar.set (Calendar.MINUTE,iminute);
        }
        if(isecond!=null){
            calendar.set ( Calendar.SECOND,isecond );
        }
        time = calendar.getTime ();
        return time;
    }
    public Integer getWyear(){
        if(time!=null){
            calendar.setTime ( time );
            wyear =  calendar.get(Calendar.YEAR);
        }
        return wyear;
    }
    public Integer getImonth(){
        if(time!=null){
            calendar.setTime ( time );
            imonth =  calendar.get(Calendar.MONTH);
        }
        return imonth;
    }
    public Integer getIday(){
        if(time!=null){
            calendar.setTime ( time );
            iday =  calendar.get(Calendar.DAY_OF_MONTH);
        }
        return iday;
    }
    public Integer getIhour(){
        if(time!=null){
            calendar.setTime ( time );
            ihour =  calendar.get(Calendar.HOUR_OF_DAY);
        }
        return ihour;
    }
    public Integer getIminute(){
        if(time!=null){
            calendar.setTime ( time );
            iminute = calendar.get(Calendar.MINUTE);
        }
        return iminute;
    }
    public Integer getIsecond(){
        if(time!=null){
            calendar.setTime ( time );
            isecond = calendar.get(Calendar.SECOND);
        }
        return isecond;
    }
    private static final long serialVersionUID = 1L;
}