# Create image based on the official Node 6 image from dockerhub
FROM httpd:2.2.32-alpine

# Get all the code needed to run the app
COPY ./app/ /usr/local/apache2/htdocs/

# Expose the port the app runs in
# Port: 80