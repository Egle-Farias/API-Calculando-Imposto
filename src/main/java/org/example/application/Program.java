package org.example.application;

import org.example.entity.Contract;
import org.example.entity.Installment;
import org.example.services.ContractService;
import org.example.services.OnlinePaymentService;
import org.example.services.PaypalService;

import java.beans.SimpleBeanInfo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;



public class Program {

    public static void main(String[] args) throws ParseException {


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data");
        System.out.println("Number: ");
        Integer number = sc.nextInt();
        System.out.println("dd/MM/yyyy: ");
        String dateString = sc.next();
        Date date;
        try {
            date = formato.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy.");
            return; // Se a data for inválida, encerre o programa ou manipule o erro conforme necessário.
        }
        System.out.println("Contract value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter number of installments: ");
        int N = sc.nextInt();

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, N);

        System.out.println("Installment: ");
        for (Installment it : contract.getInstallments()){
            System.out.println(it);
        }

        sc.close();


    }
}
