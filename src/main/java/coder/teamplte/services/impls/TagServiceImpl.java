package coder.teamplte.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coder.teamplte.dtos.TagDto;
import coder.teamplte.models.Tag;
import coder.teamplte.models.repositories.TagRepository;
import coder.teamplte.services.ImageUploadService;
import coder.teamplte.services.TagService;

@Service
public class TagServiceImpl implements TagService {

   @Autowired
   TagRepository tagRepository;
   @Autowired
   ImageUploadService imageUploadService;

   @Override
   public Tag save(TagDto tagDto) {
      String image = imageUploadService.saveFile(tagDto.getFile());
      Tag tag = new Tag(tagDto.getName(), image);
      return tagRepository.save(tag);
   }

   @Override
   public List<Tag> all() {
      return tagRepository.findAll();
   }

   @Override
   public Tag findById(int id) {
      return tagRepository.findById(id).orElse(null);
   }

   @Override
   public Tag update(Tag tag) {
      return tagRepository.save(tag);
   }

   @Override
   public void delete(Tag tag) {
      tagRepository.delete(tag);
   }

}
