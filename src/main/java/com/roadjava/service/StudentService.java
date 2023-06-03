package com.roadjava.service;

import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface StudentService {
    TableDTO retrieveStudents(StudentRequest request);
}
