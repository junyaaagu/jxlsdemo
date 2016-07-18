package com.anaguchijunya.jxls.web;

import lombok.Data;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyaanaguchi on 2016/07/16.
 */
@Controller
@RequestMapping("download")
public class JxlsDownloadController {

    @RequestMapping(value = "jxls", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> sampleJxls() throws IOException {

        System.getProperties().keySet().forEach(System.out::println);

        ClassPathResource xlsxTemplate = new ClassPathResource("xlsTemplates/example.xlsx");
        List<Customer> customers = generateCustomers();

        try (InputStream is = xlsxTemplate.getInputStream()) {
            try (OutputStream os = new FileOutputStream("/Users/junyaanaguchi/IdeaProjects/Jxlsdemo//example_output.xlsx")) {
                Context context = new Context();
                context.putVar("customers", customers);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new InputStreamResource(xlsxTemplate.getInputStream()));
    }

    public List<Customer> generateCustomers() {
        List<Customer> customerList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setId(10001);
        customer.setName("anaguchi");

        Customer customer1 = new Customer();
        customer1.setId(10002);
        customer1.setName("junya");

        customerList.add(customer);
        customerList.add(customer1);
        return customerList;
    }

    @Data
    public static class Customer {
        private long id;
        private String name;
    }
}
