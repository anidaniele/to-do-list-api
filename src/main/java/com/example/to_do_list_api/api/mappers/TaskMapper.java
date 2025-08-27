package com.example.to_do_list_api.api.mappers;

import com.example.to_do_list_api.api.dto.TaskRequestDto;
import com.example.to_do_list_api.api.dto.TaskResponseDto;
import com.example.to_do_list_api.persistence.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toDto(Task task);

    List<TaskResponseDto> toDtoList(List<Task> tasks);

    Task toEntity(TaskRequestDto taskRequestDto);

}
