package com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui;


/**
 *[{
 * 	"id": "155",
 * 	"name": "接线形式、相序、空气净距检查",
 * 	"type": "4",
 * 	"sign": "jxxsxxkqjjjc"
 * }, {
 * 	"id": "156",
 * 	"name": "电气联锁试验",
 * 	"type": "4",
 * 	"sign": "dqls"
 * }, {
 * 	"id": "157",
 * 	"name": "柜体尺寸、厚度、材质检测",
 * 	"type": "4",
 * 	"sign": "gygthdcz"
 * }, {
 * 	"id": "158",
 * 	"name": "主回路电阻测量",
 * 	"type": "4",
 * 	"sign": "gyzhldz"
 * }, {
 * 	"id": "159",
 * 	"name": "机械操作和机械特性试验",
 * 	"type": "4",
 * 	"sign": "gyjx"
 * }, {
 * 	"id": "160",
 * 	"name": "辅助和控制回路的绝缘试验",
 * 	"type": "4",
 * 	"sign": "fzhkzhljy"
 * }, {
 * 	"id": "161",
 * 	"name": "隔离开关触头镀银层厚度检测",
 * 	"type": "4",
 * 	"sign": "ctdyc"
 * }, {
 * 	"id": "162",
 * 	"name": "雷电冲击电压试验",
 * 	"type": "4",
 * 	"sign": "gyldcj"
 * }, {
 * 	"id": "163",
 * 	"name": "雷电冲击电压试验2",
 * 	"type": "4",
 * 	"sign": "gyldcj2"
 * }, {
 * 	"id": "164",
 * 	"name": "温升试验",
 * 	"type": "4",
 * 	"sign": "gywssy"
 * }, {
 * 	"id": "166",
 * 	"name": "密封试验（适用于充气柜）",
 * 	"type": "4",
 * 	"sign": "mfsyhwg"
 * }, {
 * 	"id": "196",
 * 	"name": "工频耐压试验",
 * 	"type": "4",
 * 	"sign": "gygpny"
 * }, {
 * 	"id": "197",
 * 	"name": "防护等级检验",
 * 	"type": "4",
 * 	"sign": "fhdj"
 * }]     *
 * */
public class GaoyaShiyanItem {
    public static String jxxsxxkqjjjc =("155");
    public static String dqls =("156");
    public static String gygthdcz=("157");
    public static String gyzhldz=("158");
    public static String gyjx=("159");
    public static String fzhkzhljy=("160");
    public static String ctdyc=("161");
    public static String gyldcj=("162");
    public static String gyldcj2=("163");
    public static String gywssy=("164");
    public static String mfsyhwg=("166");
    public static String gygpny=("196");
    public static String fhdj=("197");

    public static String[] jxxsxxkqjjjc_data={"d1","d2","d3","d4","d5"};
    public static String[] dqls_data={"d1","d2","d3","d4","d5","d6","d7","d8","d9","d10","d11","d12","d13","d14","d15","d16"};
    public static String[] gygthdcz_data={"d1","d2","d3"};
    public static String[] gyjx_data={"res1","res2","res3","res4","res5","res6","res7","res8"};
    public static String[] gyjx1_data={"C_TIME_A","C_TIME_B","C_TIME_C","TE","O_TIME_A","O_TIME_B","O_TIME_C","TE1","C_JUMPTIME_A","C_JUMPTIME_B","C_JUMPTIME_C"};
    public static String[] ctdyc_data={"d1","d2","d3","d4","d5","d6"};
    public static String[] fhdj_data={"ff1","res1","ff2","res2","ff3","res3","ff4","res4"};
    public static String[] mfsyhwg_data={"qtxz","qyl","hyl","tj"};
    public static String[] gyzhldz_data={"dl1","bz1","res1","dl2","bz2","res2","dl3","bz3","res3"};
    public static String[] fzhkzhljy_data={"ys1","ss1","res1","ys2","ss2","res2"};
    public static String[] gywssy_data={"CH01","CH02","CH03"};

    public static String[] gygpny_h_data={"u1","t1","res1","u2","t2","res2","u3","t3","res3"};
    public static String[] gygpny_f_data={"u4","t4","res4","u5","t5","res5","u6","t6","res6","u7","t7","res7","u8","t8","res8","u9","t9","res9"};

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
    public static String[] getGyWssyData_2(){
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
    public static String[] getGyWssyData_3(){
        String[] res = new String[10];
        int index = 0;
        for(int i = 1;i<6;i++){
            res[index++] = "cdh"+i;
            res[index++] = "cdd"+i;
        }
        return res;
    }
}
