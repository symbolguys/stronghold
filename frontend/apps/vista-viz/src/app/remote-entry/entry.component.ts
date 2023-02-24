import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'frontend-vista-viz-entry',
  template: `<canvas id="unity-canvas" width=960 height=600 style="width: 100%"></canvas>`,
})
export class RemoteEntryComponent implements OnInit {
  ngOnInit() {
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    //@ts-ignore
    createUnityInstance(document.querySelector('#unity-canvas'), {
      dataUrl: '/assets/vista/Build/vista.data',
      frameworkUrl:
        '/assets/vista/Build/vista.framework.js',
      codeUrl: '/assets/vista/Build/vista.wasm',
      streamingAssetsUrl: 'StreamingAssets',
      companyName: "Symbol Guide",
        productName: "Vista",
        productVersion: "3.1.0",
    });
  }
}
