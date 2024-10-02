package ru.practicum.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(" select i from Item i " +
            "where upper(i.name) like upper(concat('%', ?1, '%')) " +
            "   or upper(i.description) like upper(concat('%', ?1, '%'))")
    List<Item> search(String text, Long ownerId);

    @Query(" select i from Item i " +
            "where i.request.id = :requestId")
    List<Item> getItemByRequestId(@Param("requestId") Long requestId);

    @Query(" select i from Item i " +
            "where i.request.requester.id = :userId")
    List<Item> getItemByUserId(@Param("userId") Long userId);

}