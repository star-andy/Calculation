package com.qy.guowang;

import java.util.HashMap;

/**
 * Created by neilzhangxj on 17/9/2.
 */
public class StateGridCalculation {

      /**
       * 档端法 手动填写f 方法
       * @param du 度数 度
       * @param fen 度数 分
       * @param miao 度数 秒
       * @param f 实测弧垂
       * @param a 至仪器垂直中心距离
       * @param dangJu 档距
       * @return
       */
      public String sideOfItemCalculationWithDegrees(Integer dangJu, Double f, Double a, Integer du, Integer fen, Integer miao){
          Double degree = reverseDegreeDetail(du, fen, miao);
          double h = dangJu * Math.tan(Math.toRadians(degree)) - a;
          double atan = Math.atan((h - 4 * f + 4 * (Math.sqrt(a * f)))/dangJu);
          double v = Math.toDegrees(atan);
          return degreeDetail(v);
      }

      /**
       * 档外
       * @param dangJu
       * @param l 距离近杆塔距离
       * @param f 实测弧垂
       * @return
       */
      public String outSideOfItemCalculation(Integer dangJu, Double l, Double f, Integer du1, Integer fen1, Integer miao1, Integer du2, Integer fen2, Integer miao2){
          Double degree1 = reverseDegreeDetail(du1, fen1, miao1);
          Double degree2 = reverseDegreeDetail(du2, fen2, miao2);
          double a = l * Math.tan(Math.toRadians(degree2));
          double h = (dangJu + l) * Math.tan(Math.toRadians(degree1)) - a;
          double var1 = h - 4 * f - (8 * l * f / dangJu);
          double B = (2d/dangJu)*var1;
          double var2 =(8 * h * f + 16 * a * f - 16 * Math.pow(f, 2) - Math.pow(h, 2));
          double C = 1d / Math.pow(dangJu,2) * var2;
          double tanValue = B /2 + (Math.sqrt(Math.pow(B / 2, 2) + C));
          return degreeDetail(Math.toDegrees(Math.atan(tanValue)));
      }

    private static String degreeDetail(double degree) {
        String value = String.valueOf((int) degree) + "°";
        double doubleMin = degree - (int) degree;
        double min = doubleMin * 60;
        value = value + String.valueOf((int) min) + "'";
        double doubleSecond = min - (int) min;
        double second = doubleSecond * 60;
        value = value + String.valueOf(second) + "\"";
        return value;
    }

    private static Double reverseDegreeDetail(Integer du, Integer fen, Integer miao) {
        Double degree;
        degree = (double) du + ((double) fen) / 60 + ((double) miao) / 3600;
        return degree;
    }

}
