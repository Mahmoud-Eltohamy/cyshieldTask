package com.example.framework.web.utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    private CSVReader csvReader;
    private int numberOfLinesToSkip;

    public CSVFileManager(String filePath, int numberOfLinesToSkip) throws IOException {
        csvReader = new CSVReader(new FileReader(filePath));
        this.numberOfLinesToSkip = numberOfLinesToSkip;
    }

    public List<String[]> readCSVFile() throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        String[] line;
        for (int i = 0; i < numberOfLinesToSkip; i++) {
            csvReader.readNext();
        }

        while ((line = csvReader.readNext()) != null) {
            data.add(line);
        }
        return data;
    }


}
