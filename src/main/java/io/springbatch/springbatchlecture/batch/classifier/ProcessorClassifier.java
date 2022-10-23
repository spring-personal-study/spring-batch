package io.springbatch.springbatchlecture.batch.classifier;

import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C, T> implements Classifier<C, T> {


    private Map<String, ItemProcessor<ProductDTO, ApiRequestDTO>> processorMap = new HashMap<>();

    public void setProcessorMap(Map<String, ItemProcessor<ProductDTO, ApiRequestDTO>> processorMap) {
        this.processorMap = processorMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T classify(C classifiable) {
        return (T) processorMap.get(((ProductDTO) classifiable).getType());
    }
}
