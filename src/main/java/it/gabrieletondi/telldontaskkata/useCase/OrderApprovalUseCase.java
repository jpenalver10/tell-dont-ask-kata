package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class OrderApprovalUseCase {

    private final OrderRepository orderRepository;

    public OrderApprovalUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderApprovalRequest request) {

        final Order order = orderRepository.getById(request.getOrderId());
        boolean isToApproveRequest = request.isApproved();

        if (order.isShipped()) {
            throw new ShippedOrdersCannotBeChangedException();
        }

        if (isToApproveRequest && order.isRejected()) {
            throw new RejectedOrderCannotBeApprovedException();
        }

        if (!isToApproveRequest && order.isApproved()) {
            throw new ApprovedOrderCannotBeRejectedException();
        }

        order.moveStatus(request.isApproved());
        orderRepository.save(order);
    }
}
