package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Point {
    public Double x;
    public Double y;
    public Double r;
    public String currentTime;
    public String executionTime;
    public boolean result;
    public Point(Double x, Double y, Double r, String executionTime, boolean result){
        this.x=x;
        this.y=y;
        this.r=r;
        this.result=result;
        this.currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.executionTime = executionTime;
    }

    public String getR() {
        return r.toString();
    }

    public String getCurrentTime(){
        return currentTime;
    }

    public String getExecutionTime(){
        return executionTime;
    }

    public String getX() {
        Double cordX = (x*90)/r+125;
        return cordX.toString();
    }

    public String getY() {
        Double cordY = ((-1)*y*90)/r+125;
        return cordY.toString();
    }
    public String getResult(){
        String res = "";
        if(result){
            res = "true";
        }else {
            res = "false";
        }
        return res;
    }

    @Override
    public String toString() {
        return "<tr>"+"<td>"+x.toString()+"</td>"+"<td>"+y.toString()+"</td>"+"<td>"+r.toString()+"</td>"+"<td>"+getResult()+"</td>"+"<td>"+currentTime+"</td>"+"<td>"+executionTime+"</td>"+"</tr>";
    }
}