package IDE.IDE.controller;

import IDE.IDE.model.request.ReceiveOrderComponent;
import IDE.IDE.model.response.OrderResponse;
import IDE.IDE.model.request.ReceiveOrderRequest;
import IDE.IDE.model.response.ReceiveOrderResponse;
import IDE.IDE.model.request.SendOrder;
import IDE.IDE.model.response.ReceiveOrderStatusResponse;
import IDE.IDE.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/send")
    public ResponseEntity<OrderResponse> send(@RequestBody SendOrder sendOrder) {
        logger.info("Received sendOrder request: {}", sendOrder);
        OrderResponse response = orderService.sendOrder(sendOrder);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/receive")
    public ResponseEntity<List<ReceiveOrderResponse>> receive(@RequestBody List<ReceiveOrderRequest> receiveOrderRequest) {
        logger.info("Received receiveOrder request: {}", receiveOrderRequest);
        List<ReceiveOrderResponse> responses = orderService.receiveOrder(receiveOrderRequest);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/status")
    public ResponseEntity<List<ReceiveOrderStatusResponse>> status(@RequestBody List<ReceiveOrderComponent> receiveOrderComponents) {
        logger.info("Received status request: {}", receiveOrderComponents);
        List<ReceiveOrderStatusResponse> list = orderService.receiveOrderStatus(receiveOrderComponents);
        return ResponseEntity.ok(list);
    }
}
