package coder.teamplte.services;

import java.util.List;

import org.springframework.stereotype.Service;

import coder.teamplte.dtos.TagDto;
import coder.teamplte.models.Tag;

@Service
public interface TagService {
   Tag save(TagDto tagDto);
   Tag findById(int id);
   List<Tag> all();
   Tag update(Tag tag);
   void delete(Tag tag);
}
