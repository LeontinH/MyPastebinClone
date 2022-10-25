package MyPastebinClone.repository;

import MyPastebinClone.model.PasteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasteRepository extends JpaRepository<PasteModel, Long> {

}