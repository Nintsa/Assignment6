package com.example.assignment6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var userValues = mutableListOf<String>()
    private var users = mutableMapOf<String, List<String>>()
    private var nonActiveUsers = mutableSetOf<String>()
    private lateinit var firstName:String
    private lateinit var lastName:String
    private lateinit var age:String
    private lateinit var email:String
    private var nonActive: Int = 0
    private var totalUsers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addUserBtn.setOnClickListener{
            firstName = binding.firstName.text.toString()
            lastName = binding.lastName.text.toString()
            age = binding.age.text.toString()
            email = binding.mail.text.toString()

            if(users.any { it.key == email }){
                binding.result.text = "User already exists"
                binding.result.setTextColor(getColor(R.color.red))
            } else{
                userValues.add(firstName)
                userValues.add(lastName)
                userValues.add(age)
                users[email] = userValues

                if(nonActiveUsers.any{it == email}){
                    nonActiveUsers.remove(email)
                    nonActive = nonActiveUsers.size
                    binding.deletedUsers.text = "Non-Active Users: $nonActive"
                }

                binding.result.text = "User added successfully"
                binding.result.setTextColor(getColor(R.color.green))
                totalUsers = users.size
                binding.activeUsers.text = "Active Users: $totalUsers"
            }
        }

        binding.updateUserBtn.setOnClickListener{
            firstName = binding.firstName.text.toString()
            lastName = binding.lastName.text.toString()
            age = binding.age.text.toString()
            email = binding.mail.text.toString()

            if(users.any { it.key == email }){
                userValues.add(firstName)
                userValues.add(lastName)
                userValues.add(age)
                users[email] = userValues

                binding.result.text = "User updated successfully"
                binding.result.setTextColor(getColor(R.color.green))
            } else{
                binding.result.text = "User does not exist"
                binding.result.setTextColor(getColor(R.color.red))
            }
        }

        binding.removeUserBtn.setOnClickListener{
            firstName = binding.firstName.text.toString()
            lastName = binding.lastName.text.toString()
            age = binding.age.text.toString()
            email = binding.mail.text.toString()

            if(users.any { it.key == email && it.value[0] == firstName &&
                        it.value[0] == lastName && it.value[0] == age}){
                users.remove(email)
                nonActiveUsers.add(email)

                binding.result.text = "User deleted successfully"
                binding.result.setTextColor(getColor(R.color.green))

                nonActive = nonActiveUsers.size
                binding.deletedUsers.text = "Non-Active Users: $nonActive"
                totalUsers = users.size
                binding.activeUsers.text = "Active Users: $totalUsers"
            } else{
                binding.result.text = "User does not exist"
                binding.result.setTextColor(getColor(R.color.red))
            }
        }
    }
}

