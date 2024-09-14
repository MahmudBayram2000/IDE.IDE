package IDE.IDE.service;

import IDE.IDE.exception.CustomException;
import IDE.IDE.model.request.ReceiveOrderComponent;
import IDE.IDE.model.response.OrderResponse;
import IDE.IDE.model.request.ReceiveOrderRequest;
import IDE.IDE.model.response.ReceiveOrderResponse;
import IDE.IDE.model.request.SendOrder;
import IDE.IDE.model.response.ReceiveOrderStatusResponse;
import IDE.IDE.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

    public OrderResponse sendOrder(SendOrder sendOrder) {
        try {
            logger.info("sendOrder called with data: {}", sendOrder);
            List<?> result = orderRepository.sendOrder(sendOrder);
            if (result != null && result.size() >= 1) {
                String status = result.get(0).toString();
                Integer reg_id = Integer.parseInt(result.get(0).toString());
                String message = status.contains("OK") ? "OK" : "OK";
                return new OrderResponse(sendOrder.cari_id, reg_id, message);
            } else {
                throw new CustomException("Invalid result from database");
            }
        } catch (Exception e) {
            logger.error("Error in sendOrder: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ReceiveOrderResponse> receiveOrder(List<ReceiveOrderRequest> receiveOrderRequests) {
        List<ReceiveOrderResponse> orderResponses = new ArrayList<>();
        try {
            for (ReceiveOrderRequest request : receiveOrderRequests) {
                logger.info("receiveOrder called with data: {}", request);
                List<?> results = orderRepository.recieveOrder(request);
                for (Object result : results) {
                    Object[] row = (Object[]) result;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    ReceiveOrderResponse response = ReceiveOrderResponse.builder()
                            .xidmet_adi(row[1].toString())
                            .sifaris_adi(row[2].toString())
                            .pasient(row[3].toString())
                            .pasient_cins(Integer.parseInt(row[4].toString()))
                            .pasient_dogum_tarixi(LocalDate.parse(row[5].toString()))
                            .IDE_xidmet_id(Integer.parseInt(row[6].toString()))
                            .barcode(row[7].toString())
                            .gonderen_adi(row[8].toString())
                            .gonderilen_tarix(LocalDateTime.parse(row[9].toString(), formatter))
                            .build();
                    orderResponses.add(response);
                }
            }
        } catch (Exception e) {
            logger.error("Error in receiveOrder: {}", e.getMessage(), e);
            throw e;
        }
        return orderResponses;
    }

    public List<ReceiveOrderStatusResponse> receiveOrderStatus(List<ReceiveOrderComponent> receiveOrderComponents) {
        List<ReceiveOrderStatusResponse> responses = new ArrayList<>();
        try {
            for (ReceiveOrderComponent component : receiveOrderComponents) {
                logger.info("receiveOrderStatus called with data: {}", component);
                List<?> results = orderRepository.receiveOrderStatus(component);
                for (Object result : results) {
                    Integer regId = Integer.parseInt(result.toString());
                    ReceiveOrderStatusResponse receiveOrderStatusResponse = ReceiveOrderStatusResponse.builder()
                            .regId(regId)
                            .build();
                    responses.add(receiveOrderStatusResponse);
                }
            }
        } catch (Exception e) {
            logger.error("Error in receiveOrderStatus: {}", e.getMessage(), e);
            throw e;
        }
        return responses;
    }
}
