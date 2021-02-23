# Bushel Connect Documentation

This is the collection of endpoints and objects that make up the Bushel Karibou Prototype.

## Project Setup

The commands below are based off of the redoc-cli npm package.

```bash
# if you do not have Karibou on your machine, clone the repository
git clone git@bitbucket.org:myriadmobile/karibou-kotlin-prototype.git

# cd into the documentation
cd documentation

#or
cd karibou-kotlin-prototype/documentation

# use NPM to install the redoc-cli (globally)
npm install redoc-cli -g

# host the docs locally (non-static file), the file will update whenever you save a file within /documentation
redoc-cli serve openapi.yaml --watch

# generate a static html file
redoc-cli bundle openapi.yaml
```

## Redoc

More information on redoc can be found at https://www.npmjs.com/package/redoc-cli and https://github.com/Redocly/redoc/blob/master/cli/README.md
The folder structure was based off of redoc's Multi-File OpenAPI https://redoc.ly/docs/resources/multi-file-definitions/#npm
## Open API

The documentation files are based off of Open API 3 (OAS). The specification for OAS can be found https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md and https://swagger.io/specification/

## Folder Structure

WIP 