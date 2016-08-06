package com.example.jambo.smartdevicetest;

/**
 * Created by Jambo on 2016/8/6.
 */
public class Commanfun {

    public boolean isEmpty(String value){
        boolean  isOk = false;
        if (value.length() == 0 ){
            isOk = true;
        }
        return isOk;
    }


    public boolean isIPFormart(String value){
        boolean isOk = false;

        String[] values = value.split("\\.");
        if (values.length != 4){
            isOk = true;
        }

        for (int i = 0; i < values.length; i++){
            if (isDigitChar(values[i])){
                isOk = true;
                return isOk;
            }

            if (Integer.parseInt(values[i]) <= 0 || Integer.parseInt(values[i]) >= 255){
                isOk = true;
                return isOk;
            }
        }

        return isOk;
    }


    public boolean isDigitChar(String value){
        boolean isOk = false;
        for (int i = 0; i < value.length(); i++){
            if (value.charAt(i) < '0' || value.charAt(i) > '9'){
                isOk = true;
                return  isOk;
            }
        }

        return isOk;
    }


    public boolean isPortFormat(String value){
        boolean isOk = false;

        if (isDigitChar(value)) {
            isOk = true;
            return isOk;
        }

        if (Integer.parseInt(value) < 1024 || Integer.parseInt(value) > 65535){
            isOk = true;
            return isOk;
        }
        return isOk;
    }
}
