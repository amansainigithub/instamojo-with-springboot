package com.insta.mojo.inte.controllers;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/insta")
public class InstamojoController {


    @GetMapping("/getData")
    public ResponseEntity<?> getData()
    {

        ApiContext context = ApiContext.create("test_1Skp0KBq6jhkRuQPxOUmmPex8SnZeFP1u80",
                "test_1DsPgw1fe2atjO5drl2Kh0CcZS4TkqUoEBAVEjcDuOhVwIJqaeJP8Jef15pSB8LPegwffULEhOWenStRtkWNg5LKBvGCCbbbpFutb4gcpgh4ymSzDtB9MJqFeJF",
                ApiContext.Mode.TEST);
        Instamojo api = new InstamojoImpl(context);

        /*
         * Create a new payment order
         */
        PaymentOrder order = new PaymentOrder();
        order.setName("John Smith");
        order.setEmail("john.smith@gmail.com");
        order.setPhone("9848541245");
        order.setCurrency("INR");
        order.setAmount(74D);
        order.setDescription("This is a test transaction.");
        order.setRedirectUrl("http://www.someexample.com");
        order.setWebhookUrl("http://www.someurl.com/");
        order.setTransactionId("dxg234s1w");

        try {
            PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
            System.out.println(paymentOrderResponse);

        } catch (HTTPException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
            System.out.println(e.getJsonPayload());

        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }



        System.out.println("Working Api's");
        return new ResponseEntity<>(new HashMap<>() , HttpStatus.OK);
    }

}
