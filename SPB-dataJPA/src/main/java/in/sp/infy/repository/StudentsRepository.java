package in.sp.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.infy.entity.Students;
@Repository
public interface StudentsRepository extends JpaRepository<Students, Long>{

}
