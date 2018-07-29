package importexport;

import models.Book;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportXML {
    private static final String FILE_NAME = "/home/mat03/IdeaProjects/super-library/importexport/src/main/resources/export/booksSample.xlsx";
    private static final String COLUMNS[] = {"title", "releas", "isbn", "type", "pages", "description"};

    public ExportXML() {
    }

    public void setBookList(List<Book> books) {
        try {
            Workbook workbook = new XSSFWorkbook();
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Books");

            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells
            for(int i = 0; i < COLUMNS.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(COLUMNS[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            int rowNum = 1;
            for (Book book : books) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(CellType.TITLE).setCellValue(book.getTitle());
                row.createCell(CellType.RELEAS).setCellValue(book.getPublishDate());
                row.createCell(CellType.TYPE).setCellValue(book.getGenre().getText());
                row.createCell(CellType.PAGES).setCellValue(book.getNumberOfPages());
                row.createCell(CellType.DESCRIPTION).setCellValue(book.getDescription());
            }

            // Resize all columns to fit the content size
            for(int i = 0; i < COLUMNS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(new File(FILE_NAME));
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
