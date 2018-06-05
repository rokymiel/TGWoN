package com.example.rokymielsen.tgwon;

import java.util.ArrayList;
import java.util.List;

public class StorageWall {
    public List<int[]> Walls =new ArrayList<int[]>();
    public int[] postiion1= {300,20,320,250};
    public int[] postiion2= {20,370,320,390};
    public int[] postiion3= {20,770,320,790};
    public int[] postiion4= {300,910,320,1516};
    public int[] postiion5= {700,20,720,250};
    public int[] postiion6= {700,370,1920,390};
    public int[] postiion7= {1400,20,1420,370};
    public int[] postiion8= {1900,20,1920,250};
    public int[] postiion9= {700,910,720,1516};
    public int[] postiion10= {700,770,1200,790};
    public int[] postiion11= {1200,770,1220,1516};
    public int[] postiion12= {1800,910,1820,1516};
    public int[] postiion13= {1800,770,2344,790};

    public StorageWall(){

        Walls.add(postiion1);
        Walls.add(postiion2);
        Walls.add(postiion3);
        Walls.add(postiion4);
        Walls.add(postiion5);
        Walls.add(postiion6);
        Walls.add(postiion7);
        Walls.add(postiion8);
        Walls.add(postiion9);
        Walls.add(postiion10);
        Walls.add(postiion11);
        Walls.add(postiion12);
        Walls.add(postiion13);
    }
}
