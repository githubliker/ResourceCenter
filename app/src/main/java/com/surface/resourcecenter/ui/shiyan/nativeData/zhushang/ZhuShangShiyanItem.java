package com.surface.resourcecenter.ui.shiyan.nativeData.zhushang;

public class ZhuShangShiyanItem {
    /*
    * [
    {
      "id": "169",
      "name": "设计和外观检查",
      "type": "5",
      "sign": "dlqsjwg"
    },
    {
      "id": "170",
      "name": "机械操作和机械特性试验",
      "type": "5",
      "sign": "gyjx"
    },
    {
      "id": "171",
      "name": "温升试验",
      "type": "5",
      "sign": "dlqws"
    },
    {
      "id": "172",
      "name": "主回路电阻测量",
      "type": "5",
      "sign": "gyzhldz"
    },
    {
      "id": "173",
      "name": "工频耐压试验",
      "type": "5",
      "sign": "dlqgpny"
    },
    {
      "id": "174",
      "name": "雷电冲击电压试验 ",
      "type": "5",
      "sign": "gyldcj"
    },
    {
      "id": "175",
      "name": "雷电冲击电压试验2 ",
      "type": "5",
      "sign": "gyldcj2"
    },
    {
      "id": "176",
      "name": "密封试验（适用于箱式开关）",
      "type": "5",
      "sign": "dlqmfsy"
    },
    {
      "id": "177",
      "name": "辅助和控制回路的绝缘试验",
      "type": "5",
      "sign": "fzhkzhljy"
    }
  ]
    *
    * */

    public static String dlqsjwg = "169";
    public static String gyjx = "170";
    public static String dlqws = "171";
    public static String gyzhldz = "172";
    public static String dlqgpny = "173";
    public static String gyldcj = "174";
    public static String gyldcj2 = "175";
    public static String dlqmfsy = "176";
    public static String fzhkzhljy = "177";

    public static String[] dlqsjwg_data = {"d1","d2"};
    public static String[] gyjx_data = {"res1","res2","res3","res4","res5","res6","res7","res8"};
    public static String[] gyjx1_data = {"C_TIME_A","C_TIME_B","C_TIME_C","TE","O_TIME_A","O_TIME_B","O_TIME_C","TE1","C_JUMPTIME_A","C_JUMPTIME_B","C_JUMPTIME_C"};
    public static String[] dlqws_data = {"CH01","CH02","CH03"};
    public static String[] gyzhldz_data = {"dl1","bz1","res1","dl2","bz2","res2","dl3","bz3","res3"};
    public static String[] dlqgpny_data_h = {"u1","t1","res1","u2","t2","res2","u3","t3","res3"};
    public static String[] dlqgpny_data_f = {"u10","t10","res10","u11","t11","res11"};
    public static String[] dlqmfsy_data = {"status","qtxz","qyl","hyl","tj","desc","jg"};
    public static String[] fzhkzhljy_data = {"ys1","ss1","res1","ys2","ss2","res2"};

    public static String[] getLdcj_h_z(){
        String[] res = new String[6];
        int index = 0;
        for(int i = 1;i<4;i++){
            res[index++] = "zss"+i;
            res[index++] = "ztime"+i;
        }
        return res;
    }
    public static String[] getLdcj_h_f(){
        String[] res = new String[6];
        int index = 0;
        for(int i = 1;i<4;i++){
            res[index++] = "fss"+i;
            res[index++] = "ftime"+i;
        }
        return res;
    }

    public static String[] getLdcj_f_z(){
        String[] res = new String[12];
        int index = 0;
        for(int i = 4;i<10;i++){
            res[index++] = "zss"+i;
            res[index++] = "ztime"+i;
        }
        return res;
    }

    public static String[] getLdcj_f_f(){
        String[] res = new String[12];
        int index = 0;
        for(int i = 4;i<10;i++){
            res[index++] = "fss"+i;
            res[index++] = "ftime"+i;
        }
        return res;
    }
    public static String[] getDlqWssyData_2(){
        String[] res = new String[76];
        int index = 0;
        for(int i = 0;i<19;i++){
            res[index++] = "cd"+(i+1);
            if(i < 2){
                res[index++] = "CH0"+(i*3+4);
                res[index++] = "CH0"+(i*3+5);
                res[index++] = "CH0"+(i*3+6);
            } else {
                res[index++] = "CH"+(i*3+4);
                res[index++] = "CH"+(i*3+5);
                res[index++] = "CH"+(i*3+6);
            }
        }
        return res;
    }
    public static String[] getDlqWssyData_3(){
        String[] res = new String[10];
        int index = 0;
        for(int i = 1;i<6;i++){
            res[index++] = "cdh"+i;
            res[index++] = "cdd"+i;
        }
        return res;
    }
}
