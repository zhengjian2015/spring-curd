package com.zj.curd.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class WriteExcel {
	//�����������
    private String[] rowName;
    //ÿ����Ϊһ��Object����
    private List<Object[]>  dataList = new ArrayList<Object[]>();

    //���췽��������Ҫ����������
    public WriteExcel(String[] rowName,List<Object[]>  dataList){
        this.dataList = dataList;
        this.rowName = rowName;
    }
    /*
     * ��������
     * */
    public InputStream export() throws Exception{
    	 HSSFWorkbook workbook = new HSSFWorkbook();  // ��������������
    	 HSSFSheet sheet = workbook.createSheet("sheet1");	 // ����������
    	//sheet��ʽ���塾getColumnTopStyle()/getStyle()��Ϊ�Զ��巽�� - ������  - ����չ��
         HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//��ȡ��ͷ��ʽ����
         HSSFCellStyle style = this.getStyle(workbook);					//��Ԫ����ʽ����

         // ������������
         int columnNum = rowName.length;
         HSSFRow rowRowName = sheet.createRow(0);				// ������2��λ�ô�����(��˵��п�ʼ�ĵڶ���)

         // ����ͷ���õ�sheet�ĵ�Ԫ����
         for(int n=0;n<columnNum;n++){
             HSSFCell cellRowName = rowRowName.createCell(n);				//������ͷ��Ӧ�����ĵ�Ԫ��
             cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);				//������ͷ��Ԫ�����������
             HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
             cellRowName.setCellValue(text);									//������ͷ��Ԫ���ֵ
             cellRowName.setCellStyle(columnTopStyle);						//������ͷ��Ԫ����ʽ
         }

         //����ѯ�����������õ�sheet��Ӧ�ĵ�Ԫ����
         for(int i=0;i<dataList.size();i++){

             Object[] obj = dataList.get(i);//����ÿ������
             HSSFRow row = sheet.createRow(i+1);//�������������
             for(int j=0; j<obj.length; j++){
                 HSSFCell  cell = null;   //���õ�Ԫ�����������
                 cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                 if(!"".equals(obj[j]) && obj[j] != null){
                     cell.setCellValue(obj[j].toString());						//���õ�Ԫ���ֵ
                 }
                 cell.setCellStyle(style);									//���õ�Ԫ����ʽ
             }
         }
         //���п����ŵ������г��Զ���Ӧ
         for (int colNum = 0; colNum < columnNum; colNum++) {
             int columnWidth = sheet.getColumnWidth(colNum) / 256;
             for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                 HSSFRow currentRow;
                 //��ǰ��δ��ʹ�ù�
                 if (sheet.getRow(rowNum) == null) {
                     currentRow = sheet.createRow(rowNum);
                 } else {
                     currentRow = sheet.getRow(rowNum);
                 }
                 if (currentRow.getCell(colNum) != null) {
                     HSSFCell currentCell = currentRow.getCell(colNum);
                     if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                         int length = currentCell.getStringCellValue().getBytes().length;
                         if (columnWidth < length) {
                             columnWidth = length;
                         }
                     }
                 }
             }
             if(colNum == 0){
                 sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
             }else{
                 sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
             }
         }

         String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
         String headStr = "attachment; filename=\"" + fileName + "\"";
// 				        response = getResponse();
// 				        response.setContentType("APPLICATION/OCTET-STREAM");
// 				        response.setHeader("Content-Disposition", headStr);
// 				        OutputStream out = response.getOutputStream();
// 				        FileOutputStream out=new FileOutputStream("C:\\test.xls");
         ByteArrayOutputStream os=new ByteArrayOutputStream();
         try {
             workbook.write(os);
         } catch (IOException e) {
             e.printStackTrace();
         }

         byte[] content=os.toByteArray();
         InputStream is=new ByteArrayInputStream(content);
         return is;
     }
    /*
     * ��ͷ��Ԫ����ʽ
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // ��������
        HSSFFont font = workbook.createFont();
        //���������С
        font.setFontHeightInPoints((short)11);
        //����Ӵ�
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //������������
        font.setFontName("Courier New");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
   * ��������Ϣ��Ԫ����ʽ
   */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // ��������
        HSSFFont font = workbook.createFont();
        //���������С
        //font.setFontHeightInPoints((short)10);
        //����Ӵ�
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //������������
        font.setFontName("Courier New");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }
   
}
