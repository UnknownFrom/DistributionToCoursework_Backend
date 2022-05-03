package ru.itdt.mobile.sample.order.service;

import ru.itdt.mobile.sample.order.bean.StudentDTO;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.StudentPostRequest;
import ru.itdt.mobile.sample.order.bean.request.TeacherPostRequest;
import ru.itdt.mobile.sample.order.bean.response.OrderItemResponse;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;
import ru.itdt.mobile.sample.order.exception.OrderItemNotExistException;
import ru.itdt.mobile.sample.order.exception.ShopOrderNotExistException;
import ru.itdt.mobile.sample.order.mapper.PreferenceMapper;
import ru.itdt.mobile.sample.order.mapper.StudentMapper;
import ru.itdt.mobile.sample.order.mapper.TeacherMapper;
import ru.itdt.mobile.sample.order.repository.OrderItemRepository;
import ru.itdt.mobile.sample.order.repository.StudentRepository;
import ru.itdt.mobile.sample.order.repository.TeacherRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DistributionService {
    @Inject
    StudentRepository studentRepository;
    @Inject
    TeacherRepository teacherRepository;
    @Inject
    OrderItemRepository orderItemRepository;
    @Inject
    StudentMapper studentMapper;
    @Inject
    TeacherMapper teacherMapper;
    @Inject
    PreferenceMapper preferenceMapper;

    public void saveStudent(final StudentPostRequest studentPostRequest) {
        StudentDTO studentDTO = studentMapper.mapRequestToDTO(studentPostRequest);
        Student student = studentMapper.mapDTOToEntity(studentDTO);

        /*student.setPreferences(studentPostRequest.getPreferences()
                .stream()
                .map(preferenceDTO -> preferenceMapper.mapDTOToEntity(preferenceDTO, student))
                .collect(Collectors.toList()));*/
        studentRepository.save(student);
    }

    public void saveTeacher(final TeacherPostRequest teacherPostRequest) {
        TeacherDTO teacherDTO = teacherMapper.mapRequestToDTO(teacherPostRequest);
        Teacher teacher = teacherMapper.mapDTOToEntity(teacherDTO);

        /*student.setPreferences(teacherPostRequest.getPreferences()
                .stream()
                .map(preferenceDTO -> preferenceMapper.mapDTOToEntity(preferenceDTO, student))
                .collect(Collectors.toList()));*/
        teacherRepository.save(teacher);
    }

    public List<OrderItemResponse> getOrderItems(long orderId) {
        return orderItemRepository.findAllOrderItemsByShopOrderId(orderId)
                .stream()
                .map(preferenceMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrderItemResponse getOrderItem(long orderId, long productId) {
        Optional<Coursework> orderItem = orderItemRepository.findOrderItemByShopOrderId(orderId, productId);
        if (orderItem.isEmpty()) {
            throw new OrderItemNotExistException(String.format("Товара с id=%d в данном заказе не существует", productId));
        }
        return preferenceMapper.mapToResponse(orderItem.get());
    }

    public void checkAccess(long orderId, long userId) {
        Student student = studentRepository.findById(orderId);
        if (student == null) {
            throw new ShopOrderNotExistException(String.format("Заказа с id=%d не существует", orderId));
        }
    }
}
