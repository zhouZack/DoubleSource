package com.tiager.doublesource.MyTest;

import com.tiager.doublesource.DoublesourceApplicationTests;
import com.tiager.doublesource.controller.GetDataByIdFromDb;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class tigerTest extends DoublesourceApplicationTests {
    @Autowired
    private GetDataByIdFromDb getDataByIdFromDb;

    @Test
    public void getInfo(){
        getDataByIdFromDb.getDataFromDb();
    }

}
