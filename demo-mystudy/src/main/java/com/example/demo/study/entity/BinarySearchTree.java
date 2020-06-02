package com.example.demo.study.entity;

/**
 * @Author : dell .
 * @Date : 2018/7/10 09:49 .
 * @Description :
 */
public class BinarySearchTree {
    private int maxSize ;
    private int[] treeData = new int[maxSize] ;
    private int[] rightNode = new int[maxSize] ;
    private int[] leftNode = new int[maxSize] ;

    public  BinarySearchTree(int size){
        this.maxSize = maxSize;
        int[] treeData = new int[size] ;
        int[] rightNode = new int[size] ;
        int[] leftNode = new int[size] ;
        this.treeData = treeData ;
        this.rightNode = rightNode ;
        this.leftNode = leftNode ;
        //平衡二叉树
        for (int i = 0 ; i < size ; i ++){
            treeData[i] = 0 ;
            rightNode[i] = -1 ;
            leftNode[i] = 1 ;
        }
    }

    //创建二叉树
    public void createBinarySearchTree(int data ){
        System.out.println(data);
        //树的层数
        int level = 0 ;
        int positon = 0 ;
        int i ;
        for ( i = 0 ; treeData[i] != 0 ; i ++) {}
        treeData[i] = data ;
        //寻找节点位置
        while (true){
            //判断是左子树还是右子树
            if(data > treeData[level]){
                //判断右子树是否有下一层
              if(rightNode[level] != -1){
                  level = rightNode[level] ;
              } else {
                  //设定为右子树
                  positon = -1 ;
                  break;
              }
            }else {
                //判断左子树是否有下一层
                if(leftNode[level] != -1){
                    level = leftNode[level] ;
                }else {
                    //设定为左子树
                    positon = 1 ;
                    break;
                }
            }
        }
        //判定节点的左右连接
        if(positon == 1){
            //左连接
            leftNode[level] = i ;
        }else {
            //右连接
            rightNode[level] = i ;
        }
    }

    //二叉树查找法
    public int binaryTreeSearch(int key){
        System.out.println("进来了");
        int pointer  = 0 ;
        while (pointer != -1){
            if(treeData[pointer] == key){
                System.out.println(treeData[pointer] +"::::"+ pointer);
                return treeData[pointer] ;
            }else if(treeData[pointer] > key){
                pointer = leftNode[pointer] ;
            }else {
                pointer = rightNode[pointer] ;
            }
        }
        return  0 ;
    }
}
