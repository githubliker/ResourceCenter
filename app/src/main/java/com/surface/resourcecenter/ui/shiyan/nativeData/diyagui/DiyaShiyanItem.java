package com.surface.resourcecenter.ui.shiyan.nativeData.diyagui;

public class DiyaShiyanItem {

    /*
    * [
    {
      "id": "130",
      "name": "布线、操作性能和功能 ",
      "type": "9",
      "sign": "bxczxngn"
    },
    {
      "id": "131",
      "name": "提升试验 ",
      "type": "9",
      "sign": "tssy"
    },
    {
      "id": "132",
      "name": "机械碰撞试验 ",
      "type": "9",
      "sign": "jxpzsy"
    },
    {
      "id": "134",
      "name": "电气间隙和爬电距离 ",
      "type": "9",
      "sign": "dqjxpdjl"
    },
    {
      "id": "135",
      "name": "标志试验 ",
      "type": "9",
      "sign": "bzjy"
    },
    {
      "id": "136",
      "name": "电击防护和保护电路完整性 ",
      "type": "9",
      "sign": "dydjfhbhdl"
    },
    {
      "id": "137",
      "name": "介电性能试验 ",
      "type": "9",
      "sign": "dyjdxnsy"
    },
    {
      "id": "138",
      "name": "温升试验 ",
      "type": "9",
      "sign": "dywssy"
    },
    {
      "id": "139",
      "name": "机械操作试验 ",
      "type": "9",
      "sign": "kggjxcz"
    },
    {
      "id": "140",
      "name": "柜体材质、厚度及尺寸检测 ",
      "type": "9",
      "sign": "gtczhdcc"
    }
  ]
    * */

    public static String bxczxngn="130";
    public static String tssy="131";
    public static String jxpzsy="132";
    public static String dqjxpdjl="134";
    public static String bzjy="135";
    public static String dydjfhbhdl="136";
    public static String dyjdxnsy="137";
    public static String dywssy="138";
    public static String kggjxcz="139";
    public static String gtczhdcc="140";

    public static String[] getBxczxngn_data () {
        String[] res = new String[13];
        int index = 0;
        for(int i = 1;i<14;i++){
            res[index++] = "data"+i;
        }
        return res;
    }
    public static String[] getTssy_data() {
        String[] res = new String[18];
        int index = 0;
        for(int i = 1;i<19;i++){
            res[index++] = "data"+i;
        }
        return res;
    }
    public static String[] jxpzsy_data = {"data1","data2","data3","data4","data5","data6"};
    public static String[] getDqjxpdjl_data () {
        String[] res = new String[12];
        int index = 0;
        for(int i = 1;i<13;i++){
            res[index++] = "data"+i;
        }
        return res;
    }
    public static String[] bzjy_data = {"data"};
    public static String[] getDydjfhbhdl_data() {
        String[] res = new String[18];
        int index = 0;
        for(int i = 1;i<19;i++){
            res[index++] = "data"+i;
        }
        return res;
    }

    public static String[] dywssy_data = {"WS1","WS2","WS3"};
    public static String[] kggjxcz_data = {"data1","data2"};
    public static String[] gtczhdcc_data = {"data1","data2","data3"};

    public static String[] getDyjdxnsy_data(){
        String[] res = new String[24];
        for(int i = 0;i<24;i++){
            res[i] = "gp"+(i+1);
        }
        return res;
    }
    public static String[] getDyjdxnsy_Z_data(){
        String[] res = new String[25];
        int index = 0;
        for(int i = 1;i<6;i++){
            for(int m = 1;m<6;m++){
                res[index++] = "cj"+i +""+m;
            }
        }
        return res;
    }
    public static String[] getDyjdxnsy_F_data(){
        String[] res = new String[25];
        int index = 0;
        for(int i = 1;i<6;i++){
            for(int m = 1;m<6;m++){
                if(m < 3){
                    res[index++] = "cj"+i +""+m;
                } else {
                    res[index++] = "cj"+i +""+(m+3);
                }
            }
        }
        return res;
    }
    public static String[] getDyWssyData_2(){
        String[] res = new String[80];
        int index = 0;
        for(int i = 0;i<20;i++){
            res[index++] = "cd"+(i+1);
            res[index++] = "WS"+(i*3+5);
            res[index++] = "WS"+(i*3+6);
            res[index++] = "WS"+(i*3+7);
        }
        return res;
    }
    public static String[] getDyWssyData_3(){
        String[] res = new String[10];
        int index = 0;
        for(int i = 1;i<6;i++){
            res[index++] = "cdh"+i;
            res[index++] = "cdd"+i;
        }
        return res;
    }
}
