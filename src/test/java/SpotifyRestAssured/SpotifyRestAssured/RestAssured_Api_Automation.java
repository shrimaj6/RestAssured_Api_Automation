package SpotifyRestAssured.SpotifyRestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssured_Api_Automation {

	public String token;
    String userId;
    String playlistId;
	
    @BeforeTest
    public void Get_Token(){
        token="Bearer BQBVWpFAFhZ5zZbmHVv-baw_iskUbBlSkX3JnPgLTVxnHWvxewq68XAGemNw6uZdIB1BLmvb-bo8WliZLUc6URCDNUoAgIy4k42qJ51mLV41VD1xKEL3lQZepHKkm9tUQpxLGL9hgi7gO5NfghH5DWQlGvp3LxuWB7lhSZshJKqq7ik5MnRHLOqA6U_xnARWO4TOnv6l_9oMiixEyVywFQ1-F9afL3Ksl_q25dwCjRc4MDLkCLukeOqnqyH6k75I7F9kVdtAvZ26";
    }
    
 // UserProfile
    @Test
    public void Get_Current_UserProfile(){
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get("\thttps://api.spotify.com/v1/me");
        response.prettyPrint();
        userId=response.path("id");
        System.out.println("UserId :"+userId);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void Get_UserProfile() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get("\thttps://api.spotify.com/v1/users/" + userId);
        response.prettyPrint();
        String g_userId = response.path("id");
        System.out.println("UserId :" + g_userId);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }
    

    // Playlist
    @Test
    public void Playlist_Creation() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\r\n"
                		+ "  \"name\": \"New Playlist\",\r\n"
                		+ "  \"description\": \"New playlist description\",\r\n"
                		+ "  \"public\": false\r\n"
                		+ "}")
                .when()
                .post("https://api.spotify.com/v1/users/" + userId + "/playlists");
        response.prettyPrint();
        playlistId = response.path("id");
        System.out.println("Playlist Id :" + playlistId);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(201, response.getStatusCode());
    }
    
    @Test
    public void Playlist_NewAdd_Item(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\"uris\":[\"spotify:track:3hkC9EHFZNQPXrtl8WPHnX\",\"spotify:track:79hQvdTHNBkq2fp2ZrM8b2\",\"spotify:track:0tpwqQhdfJXRcUDSNRjb9O\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        String snapshot_id = response.path("snapshot_id");
        System.out.println("Snapshot Id :" + snapshot_id);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(201, response.getStatusCode());
    }
    
    @Test
    public void Playlist_Get_Playlist(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId);
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void Playlist_Get_CurrentUser_Playlist(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void Playlist_Get_Cover_Image(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/images");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    
    @Test
    public void Playlist_Get_Playlist_Item(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("\thttps://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        int total_playlist_item=response.path("total");
        System.out.println("Total Playlist item :"+total_playlist_item);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void Playlist_Get_User_Playlist(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("\thttps://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void Playlist_Update_Item(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\"range_start\":1,\"insert_before\":0,\"range_length\":1}")
                .when()
                .put("\thttps://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
//    @Test
//    public void Playlist_Update_Playlist_Detail(){
//        Response response= RestAssured.given()
//                .header("Accept","application/json")
//                .header("Content-Type","application/json")
//                .header("Authorization",token)
//                .body("{\"name\":\"Kanna playlist\",\"description\":\"Updated playlist description\",\"public\":false}")
//                .when()
//                .put("https://api.spotify.com/v1/playlists/"+playlistId);
//        response.prettyPrint();
//        System.out.println("------------------------------------------------");
//        response.then().assertThat().statusCode(200);
//        Assert.assertEquals(200,response.getStatusCode());
//    }
    @Test
    public void Playlist_U_Relete_Item(){
        Response response= RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
                .when()
                .delete("	https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    // Search

   @Test
   public void Search_Method(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("q","artist")
               .pathParam("type","track")
               .when()
               .get("https://api.spotify.com/v1/search?q={q}&type={type}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }

// Tracks
   @Test
   public void Tracks_Get_Tracks_Audio_Analysis(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id", "41Ng7LlLU9ln0sQIh2kmPz")
               .when()
               .get("https://api.spotify.com/v1/audio-analysis/{id}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   @Test
   public void Tracks_Get_Tracks_Audio_Features(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               //.queryParam("ids","3czhw7W0PTAIROkL1tcsKX,3czhw7W0PTAIROkL1tcsKX")
               .pathParam("ids","3czhw7W0PTAIROkL1tcsKX,3czhw7W0PTAIROkL1tcsKX" )
               .when()
               .get("https://api.spotify.com/v1/audio-features?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   @Test
   public void Tracks_Get_Trackss_Audio_Features(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","3czhw7W0PTAIROkL1tcsKX")
               .when()
               .get("https://api.spotify.com/v1/audio-features?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   @Test
   public void Tracks_Get_Several_Tracks(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","3czhw7W0PTAIROkL1tcsKX,5o3jMYOSbaVz3tkgwhELSV")
               .when()
               .get("https://api.spotify.com/v1/tracks?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   @Test
   public void Tracks_Get_Track(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","3czhw7W0PTAIROkL1tcsKX")
               .when()
               .get("https://api.spotify.com/v1/tracks/{ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   
// Shows
   @Test
   public void Shows_Get_Show(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","38bS44xjbVVZ3No3ByF1dJ")
               .when()
               .get("https://api.spotify.com/v1/shows/{id}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   @Test
   public void Shows_Get_Show_Episodes(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","38bS44xjbVVZ3No3ByF1dJ")
               .when()
               .get("https://api.spotify.com/v1/shows/{id}/episodes");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Shows_Get_Several_Shows(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","38bS44xjbVVZ3No3ByF1dJ,38bS44xjbVVZ3No3ByF1dJ")
               .when()
               .get("https://api.spotify.com/v1/shows?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   
   // Personalization
   @Test
   public void Personalization_Get_Users_Top_Items(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("type","artists")
               .when()
               .get("https://api.spotify.com/v1/me/top/{type}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
// Markets
@Test
public void Markets_Get_Available_Markets(){
    Response response = RestAssured.given()
            .header("Accept","application/json")
            .header("Content-Type","application/json")
            .header("Authorization",token)
            .when()
            .get("https://api.spotify.com/v1/markets");
    response.prettyPrint();
    System.out.println("------------------------------------------------");
    response.then().assertThat().statusCode(200);
    Assert.assertEquals(200,response.getStatusCode());
}


//Episode
@Test
public void Episode_Get_Episode() {
  Response response = RestAssured.given()
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .header("Authorization", token)
          .pathParam("id","512ojhOuo1ktJprKbVcKyQ")
          .when()
          .get("https://api.spotify.com/v1/episodes/{id}");
  response.prettyPrint();
  System.out.println("------------------------------------------------");
  response.then().assertThat().statusCode(200);
  Assert.assertEquals(200, response.getStatusCode());
}
  @Test
  public void Episodes_Get_Several_Episode() {
      Response response = RestAssured.given()
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .header("Authorization", token)
              .pathParam("ids","512ojhOuo1ktJprKbVcKyQ,512ojhOuo1ktJprKbVcKyQ")
              .when()
              .get("https://api.spotify.com/v1/episodes?ids={ids}");
      response.prettyPrint();
      System.out.println("------------------------------------------------");
      response.then().assertThat().statusCode(200);
      Assert.assertEquals(200, response.getStatusCode());
  }
  
  
//Browse
@Test
public void Browse_Get_Available_Genre_Seeds(){
   Response response = RestAssured.given()
           .header("Accept","application/json")
           .header("Content-Type","application/json")
           .header("Authorization",token)
           .when()
           .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
   response.prettyPrint();
   System.out.println("------------------------------------------------");
   response.then().assertThat().statusCode(200);
   Assert.assertEquals(200,response.getStatusCode());
}
   @Test
   public void Browse_Get_Several_Browse_Categories(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .when()
               .get("https://api.spotify.com/v1/browse/categories");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Browse_Get_Featured_Playlists(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .when()
               .get("https://api.spotify.com/v1/browse/featured-playlists");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Browse_Get_New_Releases(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .when()
               .get("https://api.spotify.com/v1/browse/new-releases");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Browse_Get_Recommendations(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("seed_artists","4NHQUGzhtTLFvgF5SZesLK")
               .pathParam("seed_genres","classical,country")
               .pathParam("seed_tracks","0c6xIDDpzE81m2q797ordA")

               .when()
               .get("https://api.spotify.com/v1/recommendations?seed_artists={seed_artists}&seed_genres={seed_genres}&seed_tracks={seed_tracks}");

       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   
// Artists
   @Test
   public void Artists_Get_Artists_Albums(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","5I8r2w4hf7OYp2cunjihxJ")
               .when()
               .get("https://api.spotify.com/v1/artists/{id}/albums");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Artists_Get_Artists_Related_Artists(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","5I8r2w4hf7OYp2cunjihxJ")
               .when()
               .get("https://api.spotify.com/v1/artists/{id}/related-artists");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Artists_Get_Artist(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","5I8r2w4hf7OYp2cunjihxJ")
               .when()
               .get("https://api.spotify.com/v1/artists/{id}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Artists_Get_Several_Artists(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","6eSdhw46riw2OUHgMwR8B5,5I8r2w4hf7OYp2cunjihxJ")
               .when()
               .get("https://api.spotify.com/v1/artists?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   
   
// Albums
   @Test
   public void Albums_Get_Album_Tracks(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","2pANdqPvxInB0YvcDiw4ko")
               .when()
               .get("https://api.spotify.com/v1/albums/{id}/tracks");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Albums_Get_Album(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("id","2pANdqPvxInB0YvcDiw4ko")
               .when()
               .get("https://api.spotify.com/v1/albums/{id}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
   @Test
   public void Albums_Get_Several_Albums(){
       Response response = RestAssured.given()
               .header("Accept","application/json")
               .header("Content-Type","application/json")
               .header("Authorization",token)
               .pathParam("ids","2pANdqPvxInB0YvcDiw4ko,6nlfkk5GoXRL1nktlATNsy,4hnqM0JK4CM1phwfq1Ldyz")
               .when()
               .get("https://api.spotify.com/v1/albums?ids={ids}");
       response.prettyPrint();
       System.out.println("------------------------------------------------");
       response.then().assertThat().statusCode(200);
       Assert.assertEquals(200,response.getStatusCode());
   }
    
}
