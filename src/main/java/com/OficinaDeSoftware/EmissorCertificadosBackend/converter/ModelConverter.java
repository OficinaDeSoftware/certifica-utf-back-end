package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Model;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ModelDto convertToDto(final Model model ) {

        return Objects.isNull(model) ? null : modelMapper.map( model, ModelDto.class );

    }

    public Model convertToEntity( final ModelDto dto ) {

        return Objects.isNull(dto) ? null : modelMapper.map( dto, Model.class );

    }

}
