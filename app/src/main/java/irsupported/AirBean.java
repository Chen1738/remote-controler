package irsupported;
/*
* class for getting the true data of IR sign*/
public class AirBean {
    public int myPower,myMode,mySpeed,myDierction,myTemper;
    public void AirBean(int power,int mode,int speed,int direction,int temper){
        myPower = power;
        myMode = mode;
        mySpeed = speed;
        myDierction = direction;
        myTemper = temper;
    }

    public int getMode(){

        return myMode;
    }

    public int getSpeed(){
        return mySpeed;
    }

    public int getDirection(){
        return myDierction;
    }

    public int getPower(){
        return myPower;
    }

    public int getmTemper(){
        return myTemper;
    }

    public void setMode(int data){
        //mode has heating,refrigeration,blowing and chushi
        int[] modearray = {0x01,0x02,0x03,0x04};
        data = modearray[0];
    }

    public void setSpeed(int data){
        //speed has high,middle ans low
        int[] speedarray = {0x01,0x02,0x03};
        data = speedarray[0];
   }

    public void setDirection(int data){
        //direction has right-left and up-down
        int[] directarray = {0x01,0x02};
        data = directarray[0];
    }

    public void setPower(int data){
        //power has on and off
        int[] powerarray = {0x01,0x02};
        data = powerarray[0];
    }

    public void setmTemper(int data){
        //temperature has up and down
        int[] temperarray = {0x01,0x02};
        data = temperarray[0];
    }
}
