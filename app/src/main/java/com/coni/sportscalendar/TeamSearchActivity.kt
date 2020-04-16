package com.coni.sportscalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_team_search.*
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList


class TeamSearchActivity : AppCompatActivity(), PostRecyclerAdapter.OnPostClickListener
{
    private lateinit var postAdapter: PostRecyclerAdapter
   // private
    @Override
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)
        val username = intent.getStringExtra(LoginActivity.USER_NAME)
        supportActionBar?.title = username

        CheckIfUserIsLoggedIn()
        initRecyclerView()
        fetchPosts()
    }

    private val successFetchPostsResponse = Response.Listener <JSONArray>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        Log.d("LoginActivity", "NetworkResponse : ${response.toString()}")
        val dataArray: Array<Post> = jsonParser.fromJson(response.toString(), Array<Post>::class.java)//.toCollection(ArrayList())
        val dataArrayList: ArrayList<Post> = ArrayList(listOf(*dataArray))
        postAdapter.submitList(dataArrayList)
        postAdapter.notifyDataSetChanged()
    }

    private fun fetchPosts()
    {
        Server.getInstance(this).getPosts("Kaunas",successFetchPostsResponse)
    }
    private fun initRecyclerView ()
    {
        recyclerView_Posts.apply{
            layoutManager = LinearLayoutManager (this@TeamSearchActivity)
            val spacing = TopSpacingItemDecoration(30)
            addItemDecoration(spacing)
            postAdapter = PostRecyclerAdapter(this@TeamSearchActivity)
            adapter = postAdapter
        }
    }

    private fun CheckIfUserIsLoggedIn ()
    {
        if(false)
        {
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.menu_sign_out ->
            {
                val intent = Intent(this,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            R.id.create_post ->
            {
                val intent = Intent(this,CreatePostActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostClick(position: Int)
    {
        val intent = Intent(this,CreatePostActivity::class.java)
        startActivity(intent)
    }
}