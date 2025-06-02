package com.cafe.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private RoleDTO role;       // Nested object
    private CafeDTO cafe;       // Nested object
    private Boolean isApproved;
    private Long approvedBy;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
