import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { feature } from '../../models/feature';
import { TreeTableModule } from 'primeng/treetable';
import { TreeNode } from 'primeng/api';
import { DefectListComponent } from '../defect-list/defect-list.component';
import { DefectIssue } from '../../models/defectIssue';

@Component({
  selector: 'frontend-root',
  standalone: true,
  imports: [
    CommonModule,
    TreeTableModule,
    DefectListComponent
  ],
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class RootComponent implements OnInit{

  treeNodes: TreeNode[] = new Array<TreeNode>;

  ngOnInit() {
    this.treeNodes = this.treeData();
  }

  data: feature[] = [
    {
      name: 'John',
      id: '001',
      epic: [
        {
          name: 'Epic 1',
          id: 'E001',
          task: [
            { name: 'Task 1', id: 'T001' },
            { name: 'Task 2', id: 'T002' }
          ]
        },
        {
          name: 'Epic 2',
          id: 'E002',
          task: [
            { name: 'Task 3', id: 'T003' },
            { name: 'Task 4', id: 'T004' }
          ]
        }
      ]
    },
    {
      name: 'Jane',
      id: '002',
      epic: [
        {
          name: 'Epic 3',
          id: 'E003',
          task: [
            { name: 'Task 5', id: 'T005' },
            { name: 'Task 6', id: 'T006' }
          ]
        }
      ]
    }
  ];

  treeData(): TreeNode[] {
    const treeData: TreeNode[] = [];
  
    for (const data of this.data) {
      const dataChildren: TreeNode[] = [];
  
      if (data.epic && data.epic.length > 0) {
        for (const epic of data.epic) {
          const epicChildren: TreeNode[] = [];
  
          if (epic.task && epic.task.length > 0) {
            for (const task of epic.task) {
              epicChildren.push({
                data: { 
                  name: task.name, 
                  id: task.id, 
                  routerLink: ['/task', task.id] },
              });
            }
          }
  
          dataChildren.push({
            data: { 
              name: epic.name, 
              id: epic.id,
              routerLink: ['/epic', epic.id] },
            children: epicChildren,
          });
        }
      }
  
      treeData.push({
        data: { 
          name: data.name, 
          id: data.id,
          routerLink: ['/feature', data.id]
         },
        children: dataChildren
      });
    }
    console.log(treeData)
    return treeData;
  }

  defects: DefectIssue[] = [
    {
      id: '1',
      key: 'DEF-1',
      summary: 'Application crashes on startup',
      description: 'The application crashes immediately after being launched.',
      status: 'Open',
      priority: 'High',
      assignee: 'John Doe',
      reporter: 'Jane Smith',
      created: '2022-02-20T10:30:00.000Z',
      updated: '2022-02-22T14:15:00.000Z',
      comments: [
        {
          id: '1',
          author: 'Jane Smith',
          body: 'I can reproduce this issue on my machine too.',
          created: '2022-02-20T11:45:00.000Z'
        },
        {
          id: '2',
          author: 'John Doe',
          body: 'I suspect it might be a problem with the graphics card drivers.',
          created: '2022-02-21T09:00:00.000Z'
        }
      ],
      severity: 'Critical'
    },
    {
      id: '2',
      key: 'DEF-2',
      summary: 'Buttons on the main screen do not respond',
      description: 'The buttons on the main screen do not respond when clicked.',
      status: 'In Progress',
      priority: 'Medium',
      assignee: 'Sarah Lee',
      reporter: 'John Doe',
      created: '2022-02-23T08:45:00.000Z',
      updated: '2022-02-23T14:00:00.000Z',
      comments: [
        {
          id: '1',
          author: 'John Doe',
          body: 'I think this might be related to the recent update.',
          created: '2022-02-23T09:00:00.000Z'
        },
        {
          id: '2',
          author: 'Sarah Lee',
          body: 'I will investigate and see if I can find the root cause.',
          created: '2022-02-23T11:30:00.000Z'
        }
      ],
      severity: 'Major'
    },
    {
      id: '3',
      key: 'DEF-3',
      summary: 'Incorrect data displayed in the report',
      description: 'The report is showing incorrect data for some of the fields.',
      status: 'Resolved',
      priority: 'Low',
      assignee: 'Unassigned',
      reporter: 'Jane Smith',
      created: '2022-02-22T15:00:00.000Z',
      updated: '2022-02-24T10:00:00.000Z',
      comments: [
        {
          id: '1',
          author: 'Jane Smith',
          body: 'This issue seems to have been fixed in the latest build.',
          created: '2022-02-23T13:15:00.000Z'
        }
      ],
      severity: 'Minor'
    }
  ];

}
