package com.zj.curd.poi;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author hewangtong
 * 
 */
public class ReadExcel {
    // ������
    private int totalRows = 0;
    // ������
    private int totalCells = 0;
    // ������Ϣ������
    private String errorMsg;

    // ���췽��
    public ReadExcel() {
    }

    // ��ȡ������
    public int getTotalRows() {
        return totalRows;
    }

    // ��ȡ������
    public int getTotalCells() {
        return totalCells;
    }

    // ��ȡ������Ϣ
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * ��EXCEL�ļ�����ȡ��Ϣ����
     * 
     * @param fielName
     * @return
     */
    public List<Map<String, Object>> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// ��ȡ�ļ���
//        List<Map<String, Object>> userList = new LinkedList<Map<String, Object>>();
        try {
            
            boolean isExcel2003 = true;// �����ļ����ж��ļ���2003�汾����2007�汾
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����excel��������ݶ�ȡ�ͻ���Ϣ
     * 
     * @param is      ������
     * @param isExcel2003   excel��2003����2007�汾
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// ��excel��2003ʱ,����excel2003
                wb = new HSSFWorkbook(is);
            } else {// ��excel��2007ʱ,����excel2007
                wb = new XSSFWorkbook(is);
                //System.out.println(is);
            }
            return readExcelValue(wb);// ��ȡExcel����ͻ�����Ϣ
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡExcel����ͻ�����Ϣ
     * 
     * @param wb
     * @return
     */
    private List<Map<String, Object>> readExcelValue(Workbook wb) {
        // �õ���һ��shell
        Sheet sheet = wb.getSheetAt(0);
        // �õ�Excel������
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // �õ�Excel������(ǰ����������)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        // ѭ��Excel����
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // ѭ��Excel����
            Map<String, Object> map = new HashMap<String, Object>();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // ����Ǵ�����,������д����25,cell.getNumericCellValue()�����25.0,ͨ����ȡ�ַ���ȥ��.0���25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String name = String.valueOf(cell.getNumericCellValue());
                            map.put("companyName", name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));// ����
                        } else {
                            map.put("companyName", cell.getStringCellValue());// ����
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String sex = String.valueOf(cell.getNumericCellValue());
                            map.put("companyAddress",sex.substring(0, sex.length() - 2 > 0 ? sex.length() - 2 : 1));// �Ա�
                        } else {
                            map.put("companyAddress",cell.getStringCellValue());// �Ա�
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String age = String.valueOf(cell.getNumericCellValue());
                            map.put("companyIncome", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// ����
                        } else {
                            map.put("companyIncome", cell.getStringCellValue());// ����
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String age = String.valueOf(cell.getNumericCellValue());
                            map.put("companyLogo", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// ����
                        } else {
                            map.put("companyLogo", cell.getStringCellValue());// ����
                        }
                    } else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String age = String.valueOf(cell.getNumericCellValue());
                            map.put("companySort", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// ����
                        } else {
                            map.put("companySort", cell.getStringCellValue());// ����
                        }
                    }
                }
            }
            // ��ӵ�list
            userList.add(map);
        }
        return userList;
    }

    /**
     * ��֤EXCEL�ļ�
     * 
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "�ļ�������excel��ʽ";
            return false;
        }
        return true;
    }

    // @�������Ƿ���2003��excel������true��2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @�������Ƿ���2007��excel������true��2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    
}

