package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.Member;
import java.util.ArrayList;
import java.util.List;


public class MemberLoader {

    public List<Member> loadMembers(String filePath) {

        List<Member> members = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];

                members.add(new Member(id, name));
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return members;
    }
}