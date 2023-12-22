# Quarkiverse - Elasticsearch Reactive Client

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

> [!WARNING]
> This extension is no more maintained, the latest release is 1.1.0 based on Quarkus 3.2.x.
> Maintenance stops because it depends on the Quarkus Elasticsearch High Level Client extension removed on Quarkus 3.3.



This Quarkus extension provides a Vert.x based Elasticsearch client based on [Mutiny](https://smallrye.io/smallrye-mutiny).

For more information about the reactive client, goes to the [Reactiverse Elasticsearch Client](https://github.com/reactiverse/elasticsearch-client).

To use the extension, add the following dependency to your pom.xml:

```xml
    <dependency>
      <groupId>io.quarkiverse.quarkus-elasticsearch-reactive</groupId>
      <artifactId>quarkus-elasticsearch-reactive</artifactId>
      <version>${elasticsearch-reactive.version}</version>
    </dependency>
```

Then configure it with the same configuration options as ones used by the Quarkus Elasticsearch client. 
You can read the [Configuring Elasticsearch](https://quarkus.io/guides/elasticsearch#configuring-elasticsearch) section of the Quarkus Elasticsearch guide for more information. 

Other useful articles related to Quarkus extension development can be found under the [Writing Extensions](https://quarkus.io/guides/#writing-extensions) guide category on the [Quarkus.io](http://quarkus.io) website.

Thanks again, good luck and have fun!

## Documentation

The documentation for this extension should be maintained as part of this repository and it is stored in the `docs/` directory. 

The layout should follow the [Antora's Standard File and Directory Set](https://docs.antora.org/antora/2.3/standard-directories/).

Once the docs are ready to be published, please open a PR including this repository in the [Quarkiverse Docs Antora playbook](https://github.com/quarkiverse/quarkiverse-docs/blob/main/antora-playbook.yml#L7). See an example [here](https://github.com/quarkiverse/quarkiverse-docs/pull/1).

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://www.loicmathieu.fr"><img src="https://avatars.githubusercontent.com/u/1819009?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Loïc Mathieu</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-elasticsearch-reactive/commits?author=loicmathieu" title="Code">💻</a> <a href="#maintenance-loicmathieu" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!