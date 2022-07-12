package com.test.title4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @ClassName Test.java
 * @Description 拼图
 * @createTime 2022/7/1219:58
 */
public class Test {
    // 拼图为n*m n行 m列 暂时为2行2列
    private static Integer n = 2;
    private static Integer m = 2;
    

    // 测试方法
    public static void main(String[] args) {
        // 测试
        ArrayList<List<Area>> lists = new ArrayList<>();
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(new Area(FeatureEnum.FLAT, FeatureEnum.CONCAVE, FeatureEnum.FLAT, FeatureEnum.RAISED));
        areas.add(new Area(FeatureEnum.FLAT, FeatureEnum.CONCAVE, FeatureEnum.CONCAVE, FeatureEnum.FLAT));
        ArrayList<Area> areas2 = new ArrayList<>();
        areas2.add(new Area(FeatureEnum.RAISED, FeatureEnum.FLAT, FeatureEnum.FLAT, FeatureEnum.RAISED));
        areas2.add(new Area(FeatureEnum.RAISED, FeatureEnum.FLAT, FeatureEnum.CONCAVE, FeatureEnum.FLAT));
        lists.add(areas);
        lists.add(areas2);
        System.out.println(checkIntact(lists));
    }

    /**
     * 匹配拼图的匹配情况
     * @param areas
     * @return
     */
    public static Boolean checkIntact(List<List<Area>> areas){
        if (areas.size()!=n){
            return false;   //行缺
        }
        if (!areas.stream().allMatch(e -> e.size() == n)){
            return false;   //列缺
        }
        int i = 0;  //当前判定行
        int j = 0;  //当前判定列
        return check(i, j, areas);
    }

    /**
     * 检测指定位置的拼图匹配情况
     * @param row 行坐标
     * @param col 列坐标
     * @param areas 拼图元素
     * @return
     */
    public static Boolean check(Integer row, Integer col, List<List<Area>> areas){
        // 取出当前元素
        Area area = areas.get(row).get(col);
        // 判断是第一行
        Boolean isFirstRow = row == 0;
        // 判断是第一列
        Boolean isFirstCol = col == 0;
        // 判断是最后一行
        Boolean isLastRow = row == n-1;
        // 判断是最后一列
        Boolean isLastCol = col == m-1;
        // 右侧图案匹配
        Boolean rightMatch = false;
        // 下方图案匹配
        Boolean belowMatch = false;
        // 第一行上边不为平
        if (isFirstRow && !FeatureEnum.FLAT.equals(area.getTop())){
            return false;
        }
        // 第一列左边不为平
        if (isFirstCol && !FeatureEnum.FLAT.equals(area.getLeft())){
            return false;
        }
        // 最后一行下边为平 认为下边匹配
        if (isLastRow && FeatureEnum.FLAT.equals(area.getBelow())){
            belowMatch = true;
        }
        // 最后一列右边为平 认为右侧匹配
        if (isLastCol && FeatureEnum.FLAT.equals(area.getRight())){
            rightMatch = true;
        }
        if (!isLastCol){
            // 不为最后一列匹配右侧图案
            Area right = areas.get(row).get(col+1);
            rightMatch = matchSide(area.getRight(), right.getLeft());
        }
        if (!isLastRow){
            // 不为最后一行匹配下方图案
            Area below = areas.get(row+1).get(col);
            belowMatch = matchSide(area.getBelow(), below.getTop());
        }
        // 右边和下边都匹配了
        if (rightMatch&&belowMatch){
            // 最后一行的最后一列 匹配完成
            if (isLastRow&&isLastCol) return true;
            // 最后一列 递归进入下一行第一列
            if (isLastCol) return check(++row, 0, areas);
            // 递归到右侧图案
            return check(row, ++col, areas);
        } else {
            return false;
        }
    }

    /**
     * 检测两个边是否匹配
     * @param feature1
     * @param feature2
     * @return
     */
    private static Boolean matchSide(FeatureEnum feature1, FeatureEnum feature2) {
        if (FeatureEnum.FLAT.equals(feature1)&&FeatureEnum.FLAT.equals(feature2)||
            FeatureEnum.CONCAVE.equals(feature1)&&FeatureEnum.RAISED.equals(feature2)||
            FeatureEnum.RAISED.equals(feature1)&&FeatureEnum.CONCAVE.equals(feature2)){
            return true;
        }
        return false;
    }
}

/**
 * 描述单个拼图 主要特征为四个边
 */
class Area{
    
    private FeatureEnum top;  //上
    private FeatureEnum below;  //下
    private FeatureEnum left;  //左
    private FeatureEnum right;  //右

    public FeatureEnum getTop() {
        return top;
    }

    public void setTop(FeatureEnum top) {
        this.top = top;
    }

    public FeatureEnum getBelow() {
        return below;
    }

    public void setBelow(FeatureEnum below) {
        this.below = below;
    }

    public FeatureEnum getLeft() {
        return left;
    }

    public void setLeft(FeatureEnum left) {
        this.left = left;
    }

    public FeatureEnum getRight() {
        return right;
    }

    public void setRight(FeatureEnum right) {
        this.right = right;
    }

    public Area(FeatureEnum top, FeatureEnum below, FeatureEnum left, FeatureEnum right) {
        this.top = top;
        this.below = below;
        this.left = left;
        this.right = right;
    }

    public Area() {
    }
}

/**
 * 描述边的特征
 */
enum FeatureEnum{
    FLAT("平"),
    RAISED("凸"),
    CONCAVE("凹"),
    ;
    private String desc;
    FeatureEnum(String desc) {
        this.desc = desc;
    }
}
