The app for finding and matching with other pets in purpose of having nice walks together or maybe breeding.
User is required to create the profile of his pet, add pictures, description, some fields for filtering.
Then he will be able to search through other profiles and subject to find friends for his pet.
The app should include filtering by type of pet, breed, location or searching distance.

Screens of the app:
- Authorization
- Screen with info of your pet
- Matching screen
- Filtering
- List of matches
- Chat (optional)

Technologies stack:
- MVVM
- Coroutines
- Navigation
- Dagger2
- BaaS firestore

Possible features:
- Notification when someone matches your profile
- Chat with the match
- Different ways of choosing who's to match with (stack with swiping or grid layout with pictures)

Implemented for current moment:
- Multimodule structure
- Navigation
- Room  
- Authtorization (on local db)
- Profile screen (info of your pet)
- Matching screen with cool loading animation
- Alternative matching screen (stack with swiping)
- List of matches
- All logic implemented on local db
- Call dialog instead of chat with your match

Todo:
- BaaS (authorization with matching logic)
- Chat screen