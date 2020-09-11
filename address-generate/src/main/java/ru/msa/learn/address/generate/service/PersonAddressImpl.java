package ru.msa.learn.address.generate.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.msa.learn.address.generate.models.Address;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonAddressImpl implements PersonAddress {
    private Logger log = LoggerFactory.getLogger(PersonAddressImpl.class);
    @Value("${bankAccountURL}")
    private String strinURL;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Address getRandomAddress() {
        Resposnse resposnse = restTemplate.getForEntity(strinURL, Resposnse.class).getBody();
        return resposnse.getResults().get(0).getAddress();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Resposnse {

        private List<Result> results = new ArrayList<>();

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        private static class Result {

            private Address address;

            public Address getAddress() {
                return address;
            }

            public void setAddress(Address address) {
                this.address = address;
            }

        }
    }
}
