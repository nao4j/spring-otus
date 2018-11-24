package ru.yegorpilipenko.otus.spring.m2h4.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.controller.BookController;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public final class ShortBookResourceAssembler extends ResourceAssemblerSupport<ShortBook, ShortBookResource> {

    public ShortBookResourceAssembler() {
        super(ShortBook.class, ShortBookResource.class);
    }

    @Override
    public ShortBookResource toResource(final ShortBook entity) {
        final ShortBookResource resource = new ShortBookResource(entity.getName());
        resource.add(
                linkTo(methodOn(BookController.class).getById(entity.getId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(BookController.class).getById(entity.getId()))
                        .withSelfRel()
                        .withType("DELETE")
        );
        return resource;
    }

}
