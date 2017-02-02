/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author freek
 */
public class MakeExcel {

    public static void createExcel() throws IOException, WriteException {
        try {
            String fileName = "C:\\Users\\freek\\Desktop\\file.xls";
            WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            //adding a label
            Label label = new Label(0, 0, "A label Record");
            sheet.addCell(label);

            Number number = new Number(0,1,9999);
            sheet.addCell(number);

            workbook.write();
            workbook.close();
        } catch (WriteException e) {

        }
    }
}
